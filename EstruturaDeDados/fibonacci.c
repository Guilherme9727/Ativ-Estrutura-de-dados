#include <stdio.h>

int fibonacci(int n) {

    if (n == 0)
        return 0;

    if (n == 1)
        return 1;

    return fibonacci(n - 1) + fibonacci(n - 2);
}

int main() {

    int n;

    printf("Digite a posição n: ");
    scanf("%d", &n);

    int resultado = fibonacci(n);

    printf("Fibonacci(%d) = %d\n", n, resultado);

    return 0;
}