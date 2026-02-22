#include <stdio.h>

// Função recursiva para calcular x^y
int potencia(int x, int y) {
    
    // Caso base
    if (y == 0) {
        return 1;
    }
    
    // Caso recursivo
    return x * potencia(x, y - 1);
}

int main() {
    int base, expoente;

    printf("Digite a base: ");
    scanf("%d", &base);

    printf("Digite o expoente: ");
    scanf("%d", &expoente);

    int resultado = potencia(base, expoente);

    printf("%d^%d = %d\n", base, expoente, resultado);

    return 0;
}