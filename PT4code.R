#loading raw data
train <- read.csv("train-clean.csv", header = TRUE)
test <- read.csv("test.csv", header = TRUE)


#removing note from test, due to this data not being needed to work out the instrument used at the end of the prediction.
test$frameid <- NULL
test$note <- NULL
test$playmethod <- NULL
test$class1 <- NULL
test$class2 <- NULL

#to list all of the instruments within both mixes.
#levels (train$mix1_instrument)
#levels (train$mix2_instrument)


train$mix1_instrument <- as.character(train$mix1_instrument)
train$mix2_instrument <- as.character(train$mix2_instrument)


#finds the orginal data then replaces it with the data I want.
train$mix1_instrument[train$mix1_instrument == "TenorSaxophone"] <- "Saxophone"
train$mix1_instrument[train$mix1_instrument == "ElectricGuitar"] <- "Guitar"
train$mix1_instrument[train$mix1_instrument == "DTrumpet"] <- "Trumpet"
train$mix1_instrument[train$mix1_instrument == "CTrumpet"] <- "Trumpet"
train$mix1_instrument[train$mix1_instrument == "TenorTrombone"] <- "Trombone"
train$mix1_instrument[train$mix1_instrument == "Violin"] <- "Violin"
train$mix1_instrument[train$mix1_instrument == "B-flatclarinet"] <- "Clarinet"

train$mix2_instrument[train$mix2_instrument == "TenorSaxophone"] <- "Saxophone"
train$mix2_instrument[train$mix2_instrument == "ElectricGuitar"] <- "Guitar"
train$mix2_instrument[train$mix2_instrument == "DTrumpet"] <- "Trumpet"
train$mix2_instrument[train$mix2_instrument == "CTrumpet"] <- "Trumpet"
train$mix2_instrument[train$mix2_instrument == "TenorTrombone"] <- "Trombone"
train$mix2_instrument[train$mix2_instrument == "Violin"] <- "Violin"
train$mix2_instrument[train$mix2_instrument == "B-flatclarinet"] <- "Clarinet"
train$mix2_instrument[train$mix2_instrument == "B-flatclarinet"] <- "Clarinet"
train$mix2_instrument[train$mix2_instrument == "B-FlatTrumpet"] <- "Trumpet"
train$mix2_instrument[train$mix2_instrument == "BaritoneSaxophone"] <- "Saxophone"
train$mix2_instrument[train$mix2_instrument == "BassSaxophone"] <- "Saxophone"
train$mix2_instrument[train$mix2_instrument == "SopranoSaxophone"] <- "Saxophone"
train$mix2_instrument[train$mix2_instrument == "AltoSaxophone"] <- "Saxophone"


instru = c("Accordian", "Clarinet", "Trumpet", "DoubleBass", "Oboe", "Piano", "Saxophone", "Violin", "Cello", "Tuba", "Viola",
                "Bassoon", "EnglishHorn", "French horn", "Flute", "Piccolo", "SynthBass", "Trombone")


for (instruments in instru) {
  
  if (train$mix1_instrument %in% instruments || train$mix2instrument %in% instruments) {
    
    train[,instruments] <- 1
    
  } else {
    
    train[,instruments] <- 0
  }
  
  write.table(train, file = paste0("C:\\Users\\CJ-PC\\Dropbox\\Year_Three\\Data mining\\Cleaned_data\\output\\", instruments, ".csv"), sep = ",")
             
  #train[,instru] <- NULL

}