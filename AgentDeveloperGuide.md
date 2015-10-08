Agent Developer Guide
=====================

What are Agents?
----------------
Agents are small programs that connect to sensors and push received data to
O-MI Node. 

There are two kind of Agents using different interfaces: 
* *External agents* that push O-DF formatted sensor data to a TCP port of O-MI
Node.
* *Internal agents* that can be loaded from .jar file and instantiated to be run
inside same JVM as O-MI Node.

External Agent
--------------
All you need to do is to write a program that pushes O-DF formatted data to the TCP
port defined by `application.conf`'s omi-service.external-agent-port parameter.
Program can be written with any programming language. See
[the simple python example](https://github.com/AaltoAsia/O-MI/blob/master/agentExample.py).
Starting and stopping of external agents are the user's responsibility.

Another option for writing data from outside of O-MI Node is to send O-MI write request to it. 

If your external agent is run on a different computer, you will need to add its IP-address to 
`o-mi-service.input-whitelist-ips`. You can also accept input from subnets by adding 
their masks to `o-mi-service.input-whitelist-subnets`.

There is also possibility to use Shibboleth authentication to get permission for writing.

Internal Agent
----------------
*InternalAgent* is an abstract class extending `Thread` class. They have two
abstract methods: `init` and `run`. After an `InternalAgent` is created its `init`
method is called with the string given for agent in `application.conf`. This string can
contain anything, like a path to a config file. After this `InternalAgent`'s `start` method is
called. This causes the `run` method to be run in an another thread. 

InternalAgent have also two other members: 
* `LoggingAdapter log` for logging and 
* `ActorRef loader` for communication with the `InternalAgentLoader`. 

For pushing data to database `InputPusher`'s interface is used. It has five
static public methods:
- `handleOdf` that takes an `OdfObjects` as parameter,
- `handleObjects` that takes an `Iterable` of `OdfObject` as parameter,
- `handleInfoItems` that takes an `Iterable` of `OdfInfoItem` as parameter,
- `handlePathValuePairs` that takes an `Iterable` of `(Path, OdfValue)` pairs as parameter,
- `handlePathMetaDataPairs` that takes an `Iterable` of `(Path, OdfMetaData)` pairs as parameter,

To make internal agents you need to have 
**o-mi-node.jar as a library and added to your classpath**.

`JavaAgent` and `ScalaAgent` both take an O-DF path as `config`
parameter and start pushing random generated values to that path.

Lets look at JavaAgent.java:
```java
public class JavaAgent extends InternalAgent{
    public JavaAgent() { 
    }
    private Path path;
    private Random rnd;
    private boolean initialised = false;
    public void init( String config ){
	try{
	    rnd = new Random();
            path = new Path( config );
            initialised = true;
            log.warning( "JavaAgent has been initialised." );
        }catch( Exception e ){
            log.warning( "JavaAgent has caught an exception during initialisation." );
            loader.tell( new AgentInitializationException( this, e ), null );
            InternalAgent.log.warning( "JavaAgent has died." );
        }
    }
    public void run(){
        try{
            while( !interrupted() && !path.toString().isEmpty() ){
                Date date = new java.util.Date();
                LinkedList< Tuple2< Path, OdfValue > > values = new  LinkedList< Tuple2< Path, OdfValue > >();
                Tuple2< Path, OdfValue > tuple = new Tuple2(
                        path,
                        new OdfValue(
                            Integer.toString(rnd.nextInt()), 
                            "xs:integer",
                            Option.apply( 
                                new Timestamp( 
                                    date.getTime() 
                                    ) 
                                ) 
                            ) 
                        ); 
                values.add( tuple );
                log.info( "JavaAgent pushing data." );
                InputPusher.handlePathValuePairs( values );
                Thread.sleep( 10000 );
            }
        }catch( InterruptedException e ){
            log.warning( "JavaAgent has been interrupted." );
            loader.tell( new AgentInterruption( this, e), null );
        }catch( Exception e ){
            log.warning( "JavaAgent has caught an exception." );
            loader.tell( new AgentException( this, e), null );
        }finally{
            InternalAgent.log.warning( "JavaAgent has died." );
        }
    }
}
```

In the `init` mehtod we initialise `rnd` for random value generation and save the `config`
as O-DF.

```java
    public void init( String config ){
        try{
            rnd = new Random();
            path = new Path( config );
            initialised = true;
            log.warning( "JavaAgent has been initialised." );
        }catch( Exception e ){
            log.warning( "JavaAgent has caucth exception turing initialisation." );
            loader.tell( new ThreadInitialisationException( this, e ), null );
            InternalAgent.log.warning( "JavaAgent has died." );
        }
    }
```

In the `run` method we generate a new value and push it to the `path` every ten seconds.
```java
    public void run(){
        try{
            while( !interrupted() && !path.toString().isEmpty() ){
                Date date = new java.util.Date();
                LinkedList< Tuple2< Path, OdfValue > > values = new  LinkedList< Tuple2< Path, OdfValue > >();
                Tuple2< Path, OdfValue > tuple = new Tuple2(
                        path,
                        new OdfValue(
                            Integer.toString(rnd.nextInt()), 
                            "xs:integer",
                            Option.apply( 
                                new Timestamp( 
                                    date.getTime() 
                                    ) 
                                ) 
                            ) 
                        ); 
                values.add( tuple );
                log.info( "JavaAgent pushing data." );
                InputPusher.handlePathValuePairs( values );
                Thread.sleep( 10000 );
            }
        }catch( InterruptedException e ){
            log.warning( "JavaAgent has been interrupted." );
            loader.tell( new ThreadException( this, e), null );
        }finally{
            InternalAgent.log.warning( "JavaAgent has died." );
        }
    }
```

Because O-MI Node has been writen with Scala, you may need to call Scala
code from Java. Also notice that agents need to [handle the interruption of thread
by themself and terminate itself when interrupt happens](https://docs.oracle.com/javase/tutorial/essential/concurrency/interrupt.html).

Now we have an internal agent, but to get O-MI Node to run it, we need to
compile it to a .jar file and put it to `deploy` directory. After this we have
the final step, open the `application.conf` and add new line to
`agent-system.internal-agents`: 
```
"<classname of agent>" = "<config string>"
"agents.JavaAgent" = "Objects/JavaAgent/sensor"
```

Now you need to restart O-MI Node to update its configuration.

SmartHouseAgent
---------------
SmartHouseAgent is also very simple agent that get path to config file as config string.
Config file has path to .xml file containing SmartHouse O-DF structure.
SmartHouseAgent parses xml file for O-DF, and start random generating values for OdfInfoItems.
