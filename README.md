# OpenAgent

[![license Apache-2.0](https://img.shields.io/github/license/HXSecurity/OpenAgent)](https://github.com/HXSecurity/OpenAgent/blob/main/LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/HXSecurity/OpenAgent.svg?label=Stars&logo=github)](https://github.com/HXSecurity/OpenAgent)
[![GitHub forks](https://img.shields.io/github/forks/HXSecurity/OpenAgent?label=Forks&logo=github)](https://github.com/HXSecurity/OpenAgent)
[![GitHub Contributors](https://img.shields.io/github/contributors-anon/HXSecurity/OpenAgent?label=Contributors&logo=github)](https://github.com/HXSecurity/OpenAgent)

[![CI](https://github.com/HXSecurity/OpenAgent/actions/workflows/release-agent.yml/badge.svg)](https://github.com/HXSecurity/OpenAgent/actions/workflows/release-agent.yml)
[![Github Version](https://img.shields.io/github/v/release/HXSecurity/OpenAgent?display_name=tag&include_prereleases&sort=semver)](https://github.com/HXSecurity/OpenAgent/releases)
[![Release downloads](https://shields.io/github/downloads/HXSecurity/OpenAgent/total)](https://github.com/HXSecurity/OpenAgent/releases)

OpenAgent integrates various types of Java Agent products such as IAST, RASP, SCA, soft patches, etc., to meet the needs
of enterprises in the DevSecOps process, only need to configure a Java Agent, can realize the test environment,
production environment, emergency response and other scenarios, all-round Security and service.

# Usage

* Install OpenRASP in Springboot

```shell
$ java -javaagent:/opt/openagent/agent.jar=/opt/rasp/rasp.jar&install -Dagent.mode=rasp -jar app.jar
```

* Install DongTai IAST in SpringBoot

```shell
$ java -javaagent:/opt/openagent/agent.jar=/opt/dongtai/agent.jar -Dagent.mode=iast -jar app.jar
```

* Install DongTai Soft Patch for Log4j in SpringBoot

```shell
$ java -javaagent:/opt/openagent/agent.jar=/opt/dongtai/rasp.jar&/opt/dongtai/rasp-engine.jar -Dagent.mode=soft-patch -jar app.jar
```

# Downloads

Please head to the [releases page](https://github.com/HXSecurity/OpenAgent/releases) to download a release of DongTai
OpenAgent.

# License

[Apache 2.0 License.](LICENSE)