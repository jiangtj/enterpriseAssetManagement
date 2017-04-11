pipeline {
  agent any
  stages {
    stage('git') {
      steps {
        git(url: 'https://github.com/JiangTJ/enterpriseAssetManagement.git', branch: 'master')
      }
    }
    stage('mvn') {
      steps {
        bat 'mvn clean & mvn package'
      }
    }
    stage('copy') {
      steps {
        bat 'copy target/asset.war F:/'
      }
    }
  }
}