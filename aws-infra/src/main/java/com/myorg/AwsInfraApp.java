package com.myorg;

import software.amazon.awscdk.App;

public class AwsInfraApp {

    public static void main(final String[] args) {
        App app = new App();

        MyFoodVpcStack vpcStack = new MyFoodVpcStack(app, "Vpc");
        MyFoodClusterStack clusterStack = new MyFoodClusterStack(app, "Cluster", vpcStack.getVpc());
        clusterStack.addDependency(vpcStack);

        MyFoodRdsStack rdsStack = new MyFoodRdsStack(app, "Rds", vpcStack.getVpc());
        rdsStack.addDependency(vpcStack);

        MyFoodServiceStack serviceStack = new MyFoodServiceStack(app, "Service", clusterStack.getCluster());
        serviceStack.addDependency(clusterStack);
        serviceStack.addDependency(rdsStack);

        app.synth();
    }

}

