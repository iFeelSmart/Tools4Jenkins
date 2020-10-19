## Presentation

Tools4Jenkins is a Jenkins library which allows you to setup a jenkins infrastructure as code. This library is intertwined with Tools4Dev for configuration loading and job execution.

## Jenkins configuration

Before configuring nodes and jobs, you have to create some base configuration in jenkins.

- Create credentials with `slave_default_user` as ID. This will be used for slave SSH connection.

- Create credentials with `jenkins_git_credentials` as ID. This will be used to connect to Git repositories.

- Create a `_base_` node with the following configuration :
```
Name : _base_
Remote root directory : HOME
Launch method : Launch agents via SSH
Host : IP_ADDRESS
Credentials : slave_default_user
```

- Create a `_Base_` folder at jenkins' root

- Inside `_Base_` folder, create a `_MultiBranchPipeline_` multibranch pipeline with the following configuration :
```
Branch Sources : Git :
    Project Repository : GIT_SOURCE
    Credentials : jenkins_git_credentials
    Behaviors : Filter by name (with regular expression) : Regular expression : GIT_BRANCH_FILTER
    Property strategy : Suppress automatic SCM triggerring
Build Configuration :
    Mode : by Jenkinsfile
    Script Path	: JENKINSFILE
Properties : Pipeline Libraries : Library :
    Name : main
    Branch : main
    Retrieval method : Modern SCM
    Source Code Management : Git :
        Project Repository : LIB_SOURCE
        Credentials : jenkins_git_credentials
        Behaviors : Discover branches
```

This is the base config needed to automaticaly create the configuration you will define during the next step.

## XML configuration

The file `ci-example.xml` contains the configuration of your nodes, projects and jobs to generate.
By editing this file, you will create your infrastructure at `wks sync` command invoke.
This allows you to recreate very rapidly your CI in the event of a catastrophic failure.

...