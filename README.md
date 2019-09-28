# Java API to communicate with the HTTP API of Cloud MQTT Service 

## Introduction

I had develop this API to help developers to manage their CloudMQTT configurations (add/remove/edit user, add/remove ACL ...)

## Documentations links

* Link of CloudMQTT : https://www.cloudmqtt.com/
* Link of HTTP Api documentation of CloudMQTT Instance : https://docs.cloudmqtt.com/cloudmqtt_api.html
* Link of HTTP Api documentation of CloudMQTT : https://docs.cloudmqtt.com/

## Download

I haven't added the library yet in a maven repository, but you can get the JAR of it in the release folder

## Quick started

You can do all of treatment with the class : **CloudMQTTAPI**

### Example of instanciation of **CloudMQTTAPI**

```java
CloudMQTTAPI api = new CloudMQTTAPI("yourapikey");
```

### Example of use the InstanceSectionAPI

The instance of the section can be find at :

```java
InstanceSectionAPI instanceSectionAPI = api.getInstanceAPI()
```

To restart the application call the **restart()** method as below
```java
api.getInstanceAPI().restart();
```

### Example of use the UserSectionAPI

The instance of the section can be find at :

```java
UserSectionAPI userAPI = api.getUserAPI()
```

To get all of users name in the instance call the **getUsersName()** method as below :

```java
List<String> names = api.getUserAPI().getUsersName();
```

To get details of an user in the instance call the **getUserInfo(String username)** method as below :

```java
User user = api.getUserAPI().getUserInfo("username");
```

To create an user in the instance call the **createUser(String username, String password)** method as below :

```java
api.getUserAPI().createUser("username", "password");
```

To create update the user password in the instance call the **updateUserPassword(String username, String password)** method as below :

```java
api.getUserAPI().updateUserPassword("username", "newPassword");
```

To remove an user in the instance call the **removeUser(String username)** method as below :

```java
api.getUserAPI().removeUser("username");
```

### Example of use the ACLSectionAPI

The instance of the section can be find at :

```java
ACLSectionAPI userAPI = api.getAclAPI()
```

To get all of ACL run call the **getACLRules()** method as below :

```java
List<ACL> aclRules = api.getAclAPI().getACLRules();
```

To create an acl rule topic call the **createTopicACL(String username, String topic, boolean read, boolean write)** method as below :

```java
api.getAclAPI().createTopicACL("username", "topic", true, true);
```

To create an acl rule pattern call the **createPatternACL(String pattern, boolean read, boolean write)** method as below :

```java
api.getAclAPI().createPatternACL("topic", true, false);
```

To remove an acl rule topic call the **removeACLRule(String username, String topic)** method as below :

```java
api.getAclAPI().removeACLRule("username", "topic");
```





