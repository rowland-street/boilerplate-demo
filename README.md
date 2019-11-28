# boilerplate-demo

This is the code generated by Unitly to accompany the blog post clean code (link here), which includes the video demo.

You can run the tests by running
 
```
mvn verify
```

The main method cannot be run out the box because we didnt make any environments (see other demos). To run the main you will need to create an application.config file in the src/resources folder. There is an example in the test resources. e.g.

```
anEnv {
    userTable {
        region = "eu-west-0",
        tableName = "table-name-0",
        idFieldName = "id"
    }

    userId = "aString2"
}
```

Then you will need to run the main function with an envrionment variable e.g.

```Environment=anEnv```

and of course you'll need a dynamo table that matches the settings with some users in. 

Unitily can automate all this, see the more in depth demo playlist here https://www.lightning-lambdas.com/demo.html, in particular for the deployments and cloudformation see https://www.youtube.com/watch?v=FuprJFUQvr8&t=362s
