Author: Alec Kent
Date: 4/11/2017
Class: CS 455
Assignment: HW3-PC

Assignment 3 : Using MapReduce (Hadoop) for Analytics of the US Census Dataset

As part of this assignment you will be working with datasets released by the United States Census Bureau. You will be developing 
MapReduce programs that parse and process the 1990 US Census dataset to support knowledge extraction over demographic data from 
all fifty states

All files are contained in a package cs455.Qn where n is the question number.
cs455/Main.java is the default main for the program and can be used to run any of the mapreduce algorithms.
USAGE: <input file> <output location> <question>. <question> should be in the form of Qn where n is the question number.

File List:

Q1/Main.java - runs the Q1 mapreduce 
Q1/Q1Mapper.java - mapper for Q1
Q1/Q1Reducer.java - reducer for Q1 

Q2/Main.java - runs the Q2 mapreduce 
Q2/Q2Mapper.java - mapper for Q2
Q2/Q2Reducer.java - reducer for Q2

Q3/Main.java - runs the Q3 mapreduce 
Q3/Q3Mapper.java - mapper for Q3
Q3/Q3Reducer.java - reducer for Q3 

Q4/Main.java - runs the Q4 mapreduce 
Q4/Q4Mapper.java - mapper for Q4
Q4/Q4Reducer.java - reducer for Q4

Q5/Main.java - runs the Q5 mapreduce 
Q5/Q5Mapper.java - mapper for Q5
Q5/Q5Reducer.java - reducer for Q5

Q6/Main.java - runs the Q6 mapreduce 
Q6/Q6Mapper.java - mapper for Q6
Q6/Q6Reducer.java - reducer for Q6 

Q7/Main.java - runs the Q7 mapreduce 
Q7/Q7Mapper.java - gets number of rooms and number of houses
Q7/Q7Reducer.java - calculates rooms per house for each state 
Q7/PercentileMapper.java - gets rooms per house for each state 
Q7/PercentileReducer.java - finds 95th percentile for rooms per house 

Q8/Main.java - runs the Q8 mapreduce 
Q8/Q8Mapper.java - gets overall pop and pop over 85
Q8/Q8Reducer.java - calculates percentage of people over 85 for each state
Q8/TopMapper.java - gets percentage or people over 85 for each state and sends to reducer
Q8/TopReducer.java - gets the state with the highest percentage of people over 85