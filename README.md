# Markov Chain Text Generator Project
An NLP project using a text generation algorithm based off Markov chains to generate comprehensive sentences from word sequences.

## Project Description

There are 6 classes. GenText contains the main method, processes the command line arguments, and handles errors. InvalidDataException handles the exceptions not covered by main. OutFileFormatting takes care of formatting for output string before it's writen to the file. RandomTextGenerator reads the command line arguments passed by GenText, processes it, and writes out the essay to file. Prefix class is a hashmap contains all of the methods associated with the word sequences. Successor class is a hashmap that contains all of the methods associated with the successors. 

If arguments pass through GenText successfully, RandomTextGenerator gets called to process source file. RandomTextGenerator calls Prefix class to construct the Arraylist of words as strings, then to construct the word sequences, successors, and to load the sequences and successors into their respective Hash Maps. 

Then, GenText calls RandomTextGenerator again to write the output to file. RandomWordGenerator calls Prefix to pick the word sequence to explore, pick the successor word for that word sequence to write out to file, and then determine what the next word sequence to explore is. 

Then, RandomTextGenerator formats the file via OutfileFormatting class and the output gets written to file.






