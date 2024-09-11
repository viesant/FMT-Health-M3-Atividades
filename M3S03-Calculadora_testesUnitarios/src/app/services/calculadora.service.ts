import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CalculadoraService {
  constructor() {}

  somar(a: number, b: number): number {
    return a + b;
  }
  subtrair(a: number, b: number): number {
    return a - b;
  }
  multiplicar(a: number, b: number): number {
    return a * b;
  }

  dividir(a: number, b: number): number {
    if (b === 0) {
      throw new Error('Divisão por zero');
    }
    return a / b;
  }
}
