import { Stack, StackProps, Construct, SecretValue } from '@aws-cdk/core';
import { Vpc } from '@aws-cdk/aws-ec2';
import * as cdk from '@aws-cdk/core';
import * as ecr from '@aws-cdk/aws-ecr';
import * as ecs from '@aws-cdk/aws-ecs';
import * as ecspatterns from '@aws-cdk/aws-ecs-patterns';
import * as codebuild from '@aws-cdk/aws-codebuild';
import { ManagedPolicy } from '@aws-cdk/aws-iam';
import { Artifact,  Pipeline } from '@aws-cdk/aws-codepipeline';
import { CodeBuildAction, EcsDeployAction, EcrSourceAction, CodeCommitTrigger, GitHubTrigger} from '@aws-cdk/aws-codepipeline-actions';
import { PipelineProject, LocalCacheMode } from '@aws-cdk/aws-codebuild';
import { IRepository } from '@aws-cdk/aws-ecr';
import { FargateService } from '@aws-cdk/aws-ecs';
import { SpringbootfagateStack } from './fargate';

const repoName : string = "codetest-seis";
const pipelineName :string = "PipelineSeis"

export class PipelineStack extends cdk.Stack {
  vpc:Vpc;
  fargateService: FargateService[] = [];
  sourceOutput: Artifact = new Artifact();
  buildOutput: Artifact = new Artifact();
  ecrRepository: ecr.Repository;

  constructor(scope: Construct, id: string, repoName: string, vpc: Vpc, props?: StackProps) {
    super(scope, id, props);

   

    // ECR repository
    this.ecrRepository = new ecr.Repository(this, repoName, {
      repositoryName: repoName,
    });

    this.vpc=vpc;
   
  }

  public addFargateService(service: FargateService) {
    this.fargateService.push(service);
  }

  public build(){
    var pipelineProject = this.createPipelineProject(this.ecrRepository);
    pipelineProject.role?.addManagedPolicy(ManagedPolicy.fromAwsManagedPolicyName('AmazonEC2ContainerRegistryPowerUser'));

  

    var SourceAction = this.createSourceAction(this.sourceOutput,this.ecrRepository);
    var buildAction = this.createBuildAction(pipelineProject, this.sourceOutput, this.buildOutput);
    //var ecsDeployAction = this.createEcsDeployAction(vpc, ecrRepository, buildOutput, pipelineProject);
    var deployActions = [] as EcsDeployAction[];
    this.fargateService.forEach((fargateServices) => {
      var deployAction = this.createEcsDeployAction(this.vpc, fargateServices, this.buildOutput);
      deployActions.push(deployAction);
    })

    
    var pipeline = new Pipeline(this, 'cicd_pipeline_', {
      stages: [
        {
          stageName: 'Source',
          actions: [SourceAction]
        },
        {
            stageName: 'Build',
            actions: [buildAction]
        },
        {
          stageName: 'Deploy',
          actions: deployActions
        },
      ],
      pipelineName: pipelineName,
    });
  }

   private createPipelineProject(ecrRepo: ecr.Repository): codebuild.PipelineProject {
      var pipelineProject = new codebuild.PipelineProject(this, 'cicd-codepipeline', {
        projectName: "cicd-codepipeline",
        environment: {
          buildImage: codebuild.LinuxBuildImage.UBUNTU_14_04_OPEN_JDK_8,
          privileged: true
        },
        environmentVariables: {
          "ECR_REPO": {
            value: ecrRepo.repositoryUriForTag()
          }
        },
        buildSpec: codebuild.BuildSpec.fromObject({
          version: '0.2',
          phases: {
            post_build: {
              commands: [
                "echo creating imagedefinitions.json dynamically",
                "printf '[{\"name\":\"" + repoName + "\",\"imageUri\": \"" + ecrRepo.repositoryUriForTag() + ":latest\"}]' > imagedefinitions.json",
                "echo Build completed on `date`"
              ]
            }
          },
          artifacts: {
            files: [
              "imagedefinitions.json"
            ]
          },
        }),
        cache: codebuild.Cache.local(LocalCacheMode.DOCKER_LAYER, LocalCacheMode.CUSTOM)
      });
      return pipelineProject;
    }
  

  /**
   * creates Github Source
   * @param sourceOutput where to put the clones Repository
   */
  public createSourceAction(sourceOutput: Artifact,ecrRepository:IRepository): EcrSourceAction {
    return new EcrSourceAction({
        actionName: 'Source',
        repository: ecrRepository,
        imageTag: 'latest',
        output: sourceOutput
    });
    
  }

  public createBuildAction(pipelineProject: codebuild.PipelineProject, sourceActionOutput: Artifact,
    buildOutput: Artifact): CodeBuildAction {
    var buildAction = new CodeBuildAction({
      actionName: 'Build',
      project: pipelineProject,
      input: sourceActionOutput,
      outputs: [buildOutput],
      
    });
    return buildAction;
  }

  public createEcsDeployAction(vpc: Vpc,  fargateService: FargateService, buildOutput: Artifact): EcsDeployAction {
    return new EcsDeployAction({
      actionName: 'EcsDeployAction',
      service: fargateService,
      input: buildOutput,
    })
  };

  
}