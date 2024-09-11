import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalculadoraComponent } from './calculadora.component';

describe('CalculadoraComponent', () => {
  let component: CalculadoraComponent;
  let fixture: ComponentFixture<CalculadoraComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CalculadoraComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CalculadoraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('deve alterar os valores num1 e num2 no formulário, apertar o botão de soma e retornar resultado correto do cálculo', () => {
    const spy = spyOn(component, 'somar').and.callThrough();
    const input1 = fixture.nativeElement.querySelector('#num1Input');
    const input2 = fixture.nativeElement.querySelector('#num2Input');
    const button = fixture.nativeElement.querySelector('#somarBtn');

    input1.value = 10;
    input2.value = 16;
    input1.dispatchEvent(new Event('input'));
    input2.dispatchEvent(new Event('input'));
    button.click();

    expect(spy).toHaveBeenCalled(); // Verifica se o método foi chamado com os argumentos 2 e 3
    expect(component.result).toBe(26);
  });

  it('deve subtrair 2 numeros e retornar a resposta correta', () => {
    component.num1 = 300;
    component.num2 = -25;
    component.subtrair();
    expect(component.result).toBe(325); // (result === -176)
  });

  it('deve multiplicar 2 numeros e retornar a resposta correta', () => {
    component.num1 = 15;
    component.num2 = 10;
    component.multiplicar();
    expect(component.result).toBe(150); // (result === -176)
  });

  it('deve dividir 2 numeros e retornar a resposta correta', () => {
    component.num1 = 300;
    component.num2 = 10;
    component.dividir();
    expect(component.result).toBe(30); // (result === -176)
  });
});
