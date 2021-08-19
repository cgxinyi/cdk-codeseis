import * as cdk from '@aws-cdk/core';
import * as ec2 from '@aws-cdk/aws-ec2';
import * as ecs from '@aws-cdk/aws-ecs';
import * as ecs_patterns from '@aws-cdk/aws-ecs-patterns';
import { ManagedPolicy, Role, ServicePrincipal, PolicyStatement, Effect, } from '@aws-cdk/aws-iam';

export class SpringbootfagateStack extends cdk.Stack {
  constructor(scope: cdk.Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    
    // The code that defines your stack goes here
    const vpc = new ec2.Vpc(this,"MyVpc",{
      maxAzs:2
    });
    
    const cluster = new ecs.Cluster(this,"MyCluster",{
      vpc: vpc
    });
  
    const Service = new ecs_patterns.ApplicationLoadBalancedFargateService(this, "Service", {
        cluster: cluster,
        memoryLimitMiB: 1024,
        cpu: 512,
        desiredCount: 2,
        taskImageOptions: {
          image: ecs.ContainerImage.fromAsset("../CodeTest-Seis/springbooteclipse/"),
          containerPort: 8080,
          containerName:"SeisStack",
        }
      });

      Service.targetGroup.configureHealthCheck({ path: "/actuator/health"});
      Service.taskDefinition.executionRole?.addManagedPolicy((ManagedPolicy.fromAwsManagedPolicyName('AmazonEC2ContainerRegistryPowerUser')));
    
      new cdk.CfnOutput(this, "loadBalancerUrl", {
        value: Service.loadBalancer.loadBalancerDnsName,
        exportName: "loadBalancerUrl",
      });
  }
}


