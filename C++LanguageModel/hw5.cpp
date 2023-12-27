//jayxiao
//jxiao23
#include <iostream>
#include "language_model.h"
#include <map>
#include <string>
#include <fstream>
using std::string;

int main(int argc, char* argv[]) {
    //check to make sure there is a second argument
    if (argc < 2) {
        std::cerr << "No filename argument" << std::endl;
        return 1;
    }
    //check to make sure there is a third argument
    if (argc < 3) {
        std::cerr << "No command" << std::endl;
        return 1;
    }
    //open file and make the map
   const string filename = argv[1];
   char* choice = argv[2];
   std::map<string, int> trigramCounts;
   std::ifstream inputFile(filename);
   if (!inputFile.is_open()) {
        std::cerr << "Invalid file list: " << filename << std::endl;
        return -1;
    }
    string line;
    while (inputFile >> line) {
        trigramCounts = makeTrigram(trigramCounts, line);
    }
   inputFile.close();
   int execute = 0;
   switch (*choice) {
        case 'a':
            //calls function to handle command a
            execute = handle_a(trigramCounts);
            break;
        case 'd':
            //calls function to handle command d
            execute = handle_d(trigramCounts);
            break;
        case 'c':
            //calls function to handle command c
            execute = handle_c(trigramCounts);
            break;
        case 'f':
            //calls function to handle command f
            if (argc >= 4) {
                execute = handle_f(trigramCounts, argv[3], argv[4]);
            }
            else {
                execute = -1;
                trigramCounts.clear();
                std::cerr << "Invalid argument list: f requires two additional command-line arguments" << std::endl;
            }
            break;
        default:
            //calls function to handle invalid command inputs
            trigramCounts.clear();
            std::cerr << "Invalid command: valid options are a, d, c, and f" << std::endl;
            break;
    }
    trigramCounts.clear();
    return execute;
}