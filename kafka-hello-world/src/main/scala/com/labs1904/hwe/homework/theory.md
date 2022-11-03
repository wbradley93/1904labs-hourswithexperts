# Overview

Kafka has many moving pieces, but also has a ton of helpful resources to learn available online. In this homework, your
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
#### What problem does Kafka help solve? Use a specific use case in your answer 
* Helpful resource: [Confluent Motivations and Use Cases](https://youtu.be/BsojaA1XnpM)
> It allows for real-time processing of event-driven data by a distributed system, eg monitoring financial transactions for fraud.

#### What is Kafka?
* Helpful resource: [Kafka in 6 minutes](https://youtu.be/Ch5VhJzaoaI)
> Kafka is a distributed queue-based data system.

#### Describe each of the following with an example of how they all fit together: 
 * Topic
> A group of partitions handling the same type of data
 * Producer
> A process that receives/reads event data and writes it to a topic
 * Consumer 
> A process that consumes data from a topic by maintaining partition identifiers and record offsets for each
 * Broker
> A machine holding one or more partitions of a topic (or multiple topics)
 * Partition
> A subset of the data in a topic, split according to a partition key (one of the fields in a topic's records)

#### Describe Kafka Producers and Consumers
> Producers write to topics, consumers read from topics

#### How are consumers and consumer groups different in Kafka? 
* Helpful resource: [Consumers](https://youtu.be/lAdG16KaHLs)
* Helpful resource: [Confluent Consumer Overview](https://youtu.be/Z9g4jMQwog0)
> Consumer groups are sets of consumers that share the work of reading from a topic - each partition can be read by only one consumer, so consumer groups
> containing more consumers than there are partitions on a topic will have inactive consumers (that is, the number of consumers in a consumer group reading 
> from a topic with _i_ partitions is functionally bounded by [1,_i_]). Consumer groups store records of offsets read in a topic called `__consumer_offsets`,
> allowing any consumer that goes down to pick up where it left off when it comes back up. Records are read in order within a partition, but partitions 
> are read in parallel by consumers in a consumer group.

#### How are Kafka offsets different than partitions? 
> Partitions are subsets of a topic, offsets keep track of the last record in a partition read by a consumer

#### How is data assigned to a specific partition in Kafka? 
> By the value found in a record field chosen (by the application or by Kafka, randomly) as the _partition key_

#### Describe immutability - Is data on a Kafka topic immutable? 
> Yes, records can only be written to and read endlessly from the topic - they are destroyed only according to retention policies.

#### How is data replicated across brokers in kafka? If you have a replication factor of 3 and 3 brokers, explain how data is spread across brokers
* Helpful resource [Brokers and Replication factors](https://youtu.be/ZOU7PJWZU9w)
> For a Kafka system with _j_ brokers, a replication factor (functionally limited to [1,_j_], meaningfully limited to (1,_j_]) specifies the number of 
> brokers across which a partition will be copied - in the above example, all 3 brokers will therefore hold full copies of the topic.

#### What was the most fascinating aspect of Kafka to you while learning? 
>