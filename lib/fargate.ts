import * as cdk from '@aws-cdk/core';
import * as ec2 from '@aws-cdk/aws-ec2';
import * as ecs from '@aws-cdk/aws-ecs';
import * as ecr from '@aws-cdk/aws-ecr';
import * as ecs_patterns from '@aws-cdk/aws-ecs-patterns';
import { ManagedPolicy, Role, ServicePrincipal, PolicyStatement, Effect } from '@aws-cdk/aws-iam';
import { Vpc } from '@aws-cdk/aws-ec2';
import { Peer, Port } from '@aws-cdk/aws-ec2';
import { Cluster } from '@aws-cdk/aws-ecs';
import { Stack, StackProps, Construct } from '@aws-cdk/core';

const repoName : string = "codetest-seis";

export class SpringbootfagateStack extends cdk.Stack {
  vpc: Vpc;
  repoName: string;

  constructor(scope: Construct, id: string, repoName: string, vpc: Vpc, props?: StackProps) {
    super(scope, id, props);
  }
    
    public createLoadBalancedFargateService() {
      var cluster = new Cluster(this, 'Cluster', {
        clusterName: 'Cluster',
        vpc: this.vpc
      });
  
      const ecrRepository = new ecr.Repository(this, repoName, {
        repositoryName: repoName,
      });

      var fargateService = new ecs_patterns.ApplicationLoadBalancedFargateService(this, 'Service', {
        serviceName: 'Service',
        cluster: cluster,
        memoryLimitMiB: 2048,
        cpu: 1024,
        desiredCount: 1,
        assignPublicIp: true,
        listenerPort: 8080,
        publicLoadBalancer: true,
        taskImageOptions: {
          containerName: this.repoName,
          image: ecs.ContainerImage.fromEcrRepository(ecrRepository),
          containerPort: 8080,
        },
      });
  
      fargateService.taskDefinition.executionRole?.addManagedPolicy((ManagedPolicy.fromAwsManagedPolicyName('AmazonEC2ContainerRegistryPowerUser')));
  
      fargateService.targetGroup.configureHealthCheck({
        path: "/actuator/health",
        healthyHttpCodes: "200",
        port: "8080"
      });
  
      return fargateService.service;
    }
  }