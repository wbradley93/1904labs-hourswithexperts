# Overview

Similar to the work you did for Kafka, this is your crash course into Spark through different questions. In this homework, your
challenge is to write answers that make sense to you, and most importantly, **in your own words!**
Two of the best skills you can get from this class are to find answers to your questions using any means possible, and to
reword confusing descriptions in a way that makes sense to you. 

### Tips
* You don't need to write novels, just write enough that you feel like you've fully answered the question
* Use the helpful resources that we post next to the questions as a starting point, but carve your own path by searching on Google, YouTube, books in a library, etc to get answers!
* We're here if you need us. Reach out anytime if you want to ask deeper questions about a topic 
* This file is a markdown file. We don't expect you to do any fancy markdown, but you're welcome to format however you like
* Spark By Examples is a great resources to start with - [Spark By Examples](https://sparkbyexamples.com/)

### Your Challenge
1. Create a new branch for your answers 
2. Complete all of the questions below by writing your answers under each question
3. Commit your changes and push to your forked repository

## Questions
#### What problem does Spark help solve? Use a specific use case in your answer 
* Helpful resource: [Apache Spark Use Cases](https://www.toptal.com/spark/introduction-to-apache-spark)
* [Overivew of Apache Spark](https://www.youtube.com/watch?v=znBa13Earms&t=42s)
> Spark aims to make large-scale data processing faster (through lazy, in-memory storage and processing of data, including
> streaming data), more flexible (with apis for a range of popular languages and libraries for advanced analytics), and 
> more resilient (with fault-tolerant temporary storage of data being processed) than existing solutions like Hadoop's 
> MapReduce. This can make large-scale data processing tasks, like fraudulent transaction detection, much faster.

#### What is Apache Spark?
* Helpful resource: [Spark Overview](https://www.youtube.com/watch?v=ymtq8yjmD9I) 
> Spark is a data processing engine designed to quickly process large amounts of data in a variety of environments and contexts.

#### What is distributed data processing? How does it relate to Apache Spark?  
[Apache Spark for Beginners](https://medium.com/@aristo_alex/apache-spark-for-beginners-d3b3791e259e)
> Spark is built on the notion of a resilient distributed dataset, which means that data is partitioned and stored among
> the nodes of a cluster to be processed in parallel

#### On the physical side of a spark cluster, you have a driver and executors. Define each and give an example of how they work together to process data
> Drivers manage the orchestration and supervision of both worker nodes containing executors and the processing tasks
> given to those executors to perform on the relevant data partitions

#### Define each and explain how they are different from each other 
* RDD (Resilient Distributed Dataset)
* DataFrame
* DataSet
> RDDs are collections of data partitioned across nodes in a cluster in a fault-tolerant way. Dataframes are structured
> RDDs organized into columns, similar to a relational table. Datasets extend the Dataframe API to allow for unstructured
> data, the use of the RDD API, and the performance benefits of a query optimizer.

#### What is a spark transformation?
[Spark By Examples-Transformations](https://sparkbyexamples.com/apache-spark-rdd/spark-rdd-transformations/)
> Spark transformations are lazy operations performed on RDDs that output one or more new RDDs.

#### What is a spark action? How do actions differ from transformations? 
> Spark actions return a value, while transformations return one or more RDDs. Transformations are lazy, so they are simply
> stored in memory until an action is performed that depends on them, then they only process the data relevant to the output
> of the action.

#### What is a partition in spark? Why would you ever need to repartition? 
[Spark Partitioning](https://sparkbyexamples.com/spark/spark-repartition-vs-coalesce/)
> Data partitions are the subsets of an RDD/Dataframe/Dataset distributed among the nodes of a cluster. The number of 
> partitions can impact the speed of parallelized operations - more executors processing smaller partitions of data can
> often be faster than a smaller number of executors processing larger partitions of data.

#### What was the most fascinating aspect of Spark to you while learning? 
>