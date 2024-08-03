#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    const char *env_var_names[] = 
        {"USER", "HOME", "HOST", "ARCH", "DISPLAY", "PRINTER", "PATH"};

    printf("Program to GET and SET Environment Variables\n\n");

    for (int i=0; i<sizeof(env_var_names)/sizeof(env_var_names[0]); i++) {
        char *env_var = getenv(env_var_names[i]);
        printf("%s: %s\n", env_var_names[i], env_var ? env_var : "null");
    }
    
    printf("\n\nSetting new Environment Variables \"NEW_USER\" & \"NEW_HOST\"\n\n");
    
    const char *new_env_var_names[] = {"NEW_USER", "NEW_HOST"};
    if (setenv(new_env_var_names[0], "Apostos", 1) < 0) {
        printf("Error setting new Environment Variable 1.");   
    }
    if (setenv(new_env_var_names[1], "Clara", 1) < 0) {
        printf("Error setting new Environment Variable 2.");   
    }

    for (int i=0; i<sizeof(new_env_var_names)/sizeof(new_env_var_names[0]); i++) {
        char *new_env_var = getenv(new_env_var_names[i]);
        printf("%s: %s\n", new_env_var_names[i], new_env_var ? new_env_var : "null");
    }

    return 0;
}