//jayxiao
//jxiao23
#include <iostream>
#include <string>
#include <fstream>
#include <map>
#include <vector>
#include <algorithm>
#include <sstream>

using std::string;
using std::map;
using std::cout;
using std::endl;

map<string, int> makeTrigram(map<string, int> passMap, const string& filename) {
    //open the file
    std::ifstream inputFile(filename);

    if (!inputFile.is_open()) {
        std::cerr << "Invalid file: " << filename << endl;
        return passMap;
    }

    std::vector<string> fileWords;
    string word;
    fileWords.push_back("<START_1>");
    fileWords.push_back("<START_2>");
    while (inputFile >> word) {
        fileWords.push_back(word);
    }
    fileWords.push_back("<END_1>");
    fileWords.push_back("<END_2>");
    // Check if any errors occurred during reading
    if (inputFile.bad()) {
        std::cerr << "Error: An error occurred while reading the file: " << filename << endl;
        return passMap;
    }
    //concatenate trigrams and add to map
    for (size_t i = 0; i < fileWords.size() - 2; ++i) {
        string trigram = fileWords[i] + " " + fileWords[i + 1] + " " + fileWords[i + 2];
        passMap[trigram]++;
    }

    return passMap;
}

int handle_a(map<string, int> trigramCounts) {
    //output a list of all trigrams found in the training data
    //along with their frequency counts
    //ordered lexicographically by trigram in ascending order
    for (const auto& pair : trigramCounts) {
        cout << pair.second << " - [" << pair.first << "]" << endl;
    }

   return 0;
}

int handle_d(map<string, int> trigramCounts) {
    //output descending alphabetical order trigrams
    //print out in descending order
    for (auto i = trigramCounts.rbegin(); i != trigramCounts.rend(); ++i) {
        cout << i->second << " - [" << i->first << "]" << endl;
    }

    return 0;
}

int handle_c(map<string, int> trigramCounts) {
    //ordered decreasing by counts of the trigrams
    while (!trigramCounts.empty()) {
        // Initialize variables to track the highest value and its associated key
        int highestCount = trigramCounts.begin()->second;
        string highestTrigram = trigramCounts.begin()->first;

        // Find the highest value in the map
        for (const auto& pair : trigramCounts) {
            if (pair.second > highestCount) {
                highestCount = pair.second;
                highestTrigram = pair.first;
            }
        }

        // Print the highest value and its key
        cout << highestCount << " - [" << highestTrigram << "]" << endl;

        // Erase the key-value pair with the highest value
        trigramCounts.erase(highestTrigram);
    }
    return 0;
}

int handle_f(map<string, int> trigramCounts, const string& wordx, const string& wordy) {
    //return the most common third word given 2 inputs
    string subString = wordx + " " + wordy;
    map<string, int> substringCounts;
    size_t n = subString.length();
    for (const auto& pair : trigramCounts) {
       if (subString == (pair.first).substr(0,n)) {
            substringCounts[pair.first] = pair.second;
        }
    }
    if (substringCounts.size() == 0) {
        cout << "No trigrams of the form [" << wordx << " " << wordy << " ? ]" << endl;
    }
    int highestCount = 0;
    string highestTrigram = " ";
    for (const auto& pair : substringCounts) {
            if (pair.second > highestCount) {
                highestCount = pair.second;
                highestTrigram = pair.first;
            }
    }
    cout << highestCount << " - [" << highestTrigram << "]" << endl;
    return 0;
}