import { CdkPipeline, CodeBuildStep, SimpleSynthAction, UpdatePipelineAction } from '@aws-cdk/pipelines';
import * as codepipeline from '@aws-cdk/aws-codepipeline';
import * as codepipeline_actions from '@aws-cdk/aws-codepipeline-actions';
import { WorkshopPipelineStage } from './pipeline-stage';
import * as cdk from '@aws-cdk/core';
import { ManagedPolicy, Role, ServicePrincipal, PolicyStatement, Effect, } from '@aws-cdk/aws-iam';
import * as iam from '@aws-cdk/aws-iam';
import * as ecr from '@aws-cdk/aws-ecr';
import * as ecs from '@aws-cdk/aws-ecs';
import * as ecspatterns from '@aws-cdk/aws-ecs-patterns';

const repoName : string = "SeisStack";

export class SpringbootpipelineStack extends cdk.Stack {
    constructor(scope: cdk.Construct, id: string, props?: cdk.StackProps) {
      super(scope, id, props);
  
    const ecrRepository = new ecr.Repository(this, repoName, {
        repositoryName: repoName,
      });

      
    const sourceArtifact = new codepipeline.Artifact();
    const cloudAssemblyArtifact = new codepipeline.Artifact();

    
    
    const pipeline = new CdkPipeline(this, 'SeisPipeline', {
    pipelineName: 'SeisPipeline',
    cloudAssemblyArtifact,    
    sourceAction: new codepipeline_actions.GitHubSourceAction({
        actionName: 'GitHub',
        output: sourceArtifact,
        oauthToken: cdk.SecretValue.secretsManager('github-oauth'),
        // Replace these with your actual GitHub project info
        owner: 'cgxinyi',
        repo: 'CodeTest-Seis',
        branch: 'main',
    }), synthAction: SimpleSynthAction.standardNpmSynth({
        sourceArtifact,
        cloudAssemblyArtifact,
        subdirectory: 'springbootcdk',
        installCommand:'cd ../springbooteclipse && npm install -g aws-cdk typescript',
        buildCommand: 'mvn install && npm install',
        synthCommand:'npx run cdk synth',
        environment: {
        privileged:true,
        },
        rolePolicyStatements:[
        new iam.PolicyStatement({
            effect:iam.Effect.ALLOW,
            actions:[
            "sts:AssumeRole",
            ],
            resources:[
            "*"
            ]
        })
        ]
    }),
    });


        const deploy = new WorkshopPipelineStage(this, 'Deploy');
        pipeline.addApplicationStage(deploy);

    }
}