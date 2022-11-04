# Overview

By now you've seen some different Big Data frameworks such as Kafka and Spark. Now we'll be focusing in on HBase. In this homework, your
challenge is to write answers that make sense to you, and most importantly, **in your own words!**
Two of the best skills you can get from this class are to find answers to your questions using any means possible, and to
reword confusing descriptions in a way that makes sense to you. 

### Tips
* You don't need to write novels, just write enough that you feel like you've fully answered the question
* Use the helpful resources that we post next to the questions as a starting point, but carve your own path by searching on Google, YouTube, books in a library, etc to get answers!
* We're here if you need us. Reach out anytime if you want to ask deeper questions about a topic 
* This file is a markdown file. We don't expect you to do any fancy markdown, but you're welcome to format however you like


### Your Challenge
1. Create a new branch for your answers 
2. Complete all of the questions below by writing your answers under each question
3. Commit your changes and push to your forked repository

## Questions
#### What is a NoSQL database? 
> A data store that isn't organized into relational tables.

#### In your own words, what is Apache HBase? 
> HBase provides highly performant read and write capabilities for HDFS, a distributed data storage/file system.

#### What are some strengths and limitations of HBase? 
* [HBase By Examples](https://sparkbyexamples.com/apache-hbase-tutorial/)
> Strengths:
> - All the benefits of HDFS (scalable, fault tolerant, etc)
> - Supports MapReduce and Spark
> - REST API
> - Runs on commodity hardware
> 
> Limitations:
> - HBase tables cannot be joined
> - CPU/memory intensive
> - Cannot create keys on multiple columns
> - Can only sort on row keys

#### Explain the following concepts: 
* Row key
> Identifier for a subset of the data space containing a single set of timestamped key-value pairs
* Column Qualifier
> Identifier for a single key (column/dimension) within the data space
* Column Family
> Set of keys (columns/dimensions) defining a logically-related subset of the data space that is stored together on disk

#### What are the differences between Get and Put commands in HBase? 
* [HBase commands](https://www.tutorialspoint.com/hbase/hbase_create_data.htm)
> Get reads specific data from the store, put writes data to the store

#### What is the HBase Scan command for? 
* [HBase Scan](https://www.tutorialspoint.com/hbase/hbase_scan.htm)
> Reading the full table in an HBase store

#### What was the most interesting aspect of HBase when went through all the questions?
> 