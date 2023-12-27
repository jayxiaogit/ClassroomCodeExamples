#include <stdio.h>

int main(void) {
    char name[50];      
    int age;         
    char favColor[20];
    char favFood[30];  

    // Collect user information
    printf("What's your name? ");
    scanf("%s", name);  

    printf("How old are you? ");
    scanf("%d", &age); 

    printf("What is your favorite color? ");
    scanf("%s", favColor); 

    printf("What is your favorite food? ");
    scanf("%s", favFood);  

    // Output the collected information
    printf("\nName: %s\n", name);
    printf("Age: %d\n", age);
    printf("Favorite Color: %s\n", favColor);
    printf("Favorite Food: %s\n", favFood);
    
    return 0;
}
