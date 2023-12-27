//jayxiao
//jxiao23
#ifndef LANGUAGE_MODEL_H
#define LANGUAGE_MODEL_H

#include <map>
#include <string>

//returns a map of trigrams
std::map<std::string, int> makeTrigram(std::map<std::string, int> passMap, const std::string& filename);

//command a
int handle_a(std::map<std::string, int> trigramCounts);

//command d
int handle_d(std::map<std::string, int> trigramCounts);

//command c
int handle_c(std::map<std::string, int> trigramCounts);

//command f
int handle_f(std::map<std::string, int> trigramCounts, const std::string& wordx, const std::string& wordy);

#endif // LANGUAGE_MODEL_H