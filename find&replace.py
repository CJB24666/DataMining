import re, csv

# open your csv and read as a text string
with open('C:\Users\chris\Dropbox\Year_three\Data mining\assignment\Assignment\Data_Mining_assignment\cleaned_data\old\pred.csv', 'r') as f:
    my_csv_text = f.read()

find_str = 'chordophone'
replace_str = '123'


# open new file and save
new_csv_path = 'C:\Users\chris\Dropbox\Year_three\Data mining\assignment\Assignment\Data_Mining_assignments' # or whatever path and name you want
with open(new_csv_path, 'w') as f:
    f.write(new_csv_str)
