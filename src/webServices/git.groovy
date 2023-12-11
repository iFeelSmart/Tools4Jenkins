package webServices;

def setBuildStatus(_BuildStatus){
    def M_T4d = new system.tools4dev()
    withCredentials([string(credentialsId: 'jenkins_git_token', variable: 'JENKINS_GIT_TOKEN')]) {
        M_T4d.exec("_t4dTeamGitServerSetBuildStatus \"${env.M_GitSHA}\" \"${env.M_JobName}\" \"${_BuildStatus}\" \"${env.M_CurrentBuildUrl}\" ")
    }
}

def setSchemeStatus(_BuildStatus,_BuildName){
    def M_T4d = new system.tools4dev()
    withCredentials([string(credentialsId: 'jenkins_git_token', variable: 'JENKINS_GIT_TOKEN')]) {
        M_T4d.exec("_t4dTeamGitServerSetBuildStatus \"${env.M_GitSHA}\" \"${_BuildName}\" \"${_BuildStatus}\" \"${env.M_CurrentBuildUrl}\" ")
    }
}
    