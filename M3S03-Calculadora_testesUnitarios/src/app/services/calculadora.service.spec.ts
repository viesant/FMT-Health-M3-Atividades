import { TestBed } from '@angular/core/testing';

import { CalculadoraService } from './calculadora.service';

describe('CalculadoraService', () => {
  let service: CalculadoraService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CalculadoraService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('deve adicionar 2 numeros e retornar a resposta correta', () => {
    expect(service.somar(55, 21)).toBe(76);
  });

  it('deve subtrair 2 numeros e retornar a resposta correta', () => {
    expect(service.subtrair(10, 3)).toBe(7);
  });

  it('deve multiplicar 2 numeros e retornar a resposta correta', () => {
    expect(service.multiplicar(7, 2)).toBe(14);
  });
});
