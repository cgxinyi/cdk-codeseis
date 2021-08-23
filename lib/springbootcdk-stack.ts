import * as cdk from '@aws-cdk/core';
import { Vpc } from '@aws-cdk/aws-ec2';
import { FargateService } from '@aws-cdk/aws-ecs';
import { SpringbootfagateStack } from './fargate';
import { PipelineStack } from './pipeline';
import { IRepository } from '@aws-cdk/aws-ecr';
import * as ecr from '@aws-cdk/aws-ecr';

const repoName : string = "codetest-seis";

export class SpringbootcdkStack extends cdk.Stack {
  constructor(scope: cdk.Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    
    const ecrRepository = new ecr.Repository(this, repoName, {
      repositoryName: repoName,
    });

    var vpc = new Vpc(this, 'my.vpc', {
      maxAzs: 2
    });

    let springBootFargateService = new SpringbootfagateStack(this, 'Springfagate',repoName,vpc,props);
    let pipeline = new PipelineStack (this,'Pipeline',repoName,vpc, props);
    let service = springBootFargateService.createLoadBalancedFargateService();
   
    pipeline.addFargateService(service);
    pipeline.build(); 
    // The code that defines your stack goes here
  }
}
