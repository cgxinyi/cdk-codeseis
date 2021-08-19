import { SpringbootcdkStack } from './springbootcdk-stack';
import { Stage, Construct, StageProps } from '@aws-cdk/core';

export class WorkshopPipelineStage extends Stage {

    constructor(scope: Construct, id: string, props?: StageProps) {
        super(scope, id, props);

        new SpringbootcdkStack (this, "Service",{  
        });
    }

    
}