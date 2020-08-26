https://console-openshift-../k8s/ns/openshift/imagestreams

openjdk-8-rhel8 > openjdk-8-rhel8:1.2

```shell
oc new-app openjdk-8-rhel8:1.2~https://github.com/aelkz/3scale-debugger-app.git --name=debug-api --context-dir=/
oc patch svc debug-api -p '{"spec":{"ports":[{"name":"http","port":8080,"protocol":"TCP","targetPort":8080}]}}'
oc expose svc debug-api
```
