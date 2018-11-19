#loading raw data
train <- read.csv("train.csv", header = TRUE)
test<- read.csv("test.csv", header = TRUE)

#removing mix two from the train data set
train$mix2_instrument <- NULL

#removing frameid from test, due to this data not being needed to work out the instrument used at the end of the prediction.
test$frameid <- NULL

#removing note from test, due to this data not being needed to work out the instrument used at the end of the prediction.
test$note <- NULL
test$playmethod <- NULL
test$class1 <- NULL
test$class2 <- NULL

test$mix1_instrument <- NULL


test$mix1_instrument <- c("Saxophone", "Guitar", "Trumpet", "Trombone", "Violin", "Clarinet", "Piano", "DoubleBass", "Oboe", "Accordian")

test$mix1_instrument <- as.factor(test$mix1_instrument)

test$mix1_instrument -> NULL

str(test$mix1_instrument)


#not always needed but this will find the unque values of mix1_instrument
unique(train$mix1_instrument)
length(unique(as.character(train$mix1_instrument)))

#converts the mix1_instruments from a factor to a character.
train$mix1_instrument <- as.character(train$mix1_instrument)

#finds the orginal data then replaces it with the data I want.
train$mix1_instrument[train$mix1_instrument == "TenorSaxophone"] <- "Saxophone"
train$mix1_instrument[train$mix1_instrument == "ElectricGuitar"] <- "Guitar"
train$mix1_instrument[train$mix1_instrument == "DTrumpet"] <- "Trumpet"
train$mix1_instrument[train$mix1_instrument == "TenorTrombone"] <- "Trombone"
train$mix1_instrument[train$mix1_instrument == "Violin"] <- "Violin"
train$mix1_instrument[train$mix1_instrument == "CTrumpet"] <- "Trumpet"
train$mix1_instrument[train$mix1_instrument == "B-flatclarinet"] <- "Clarinet"

#converts the data back to factor
train$mix1_instrument <- as.factor(train$mix1_instrument)


instrument_pred <- knn(train = train, test = test, cl = trainLabels, k=3)


