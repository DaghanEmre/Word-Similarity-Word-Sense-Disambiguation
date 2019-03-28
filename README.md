# Word-Similarity-Word-Sense-Disambiguation
Given a word with senses and a small training set of contexts for each of the senses, apply the Naïve Bayes classifier to find the correct sense of a word in the test set.

Given two words and a few sentences containing them, your task is to compute their
cosine similarity. Use bag-of-words method with a window size of ±3 by assigning frequency
values in the feature vector. Please keep in mind that the size of your feature vector will be
equal to the number of words in the vocabulary (i.e. all the context words in a window size of 3
for the given target words). Stop words will not be excluded.

Two words are separated by a blank line. The words for which you will calculate the cosine
similarity will be delimited by “:”. Only one morphological form of the target words will exist in
the file.
