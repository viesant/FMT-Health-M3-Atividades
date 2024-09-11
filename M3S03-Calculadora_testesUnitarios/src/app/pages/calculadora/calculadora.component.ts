import { Component, inject } from '@angular/core';
import { CalculadoraService } from '../../services/calculadora.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-calculadora',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './calculadora.component.html',
  styleUrl: './calculadora.component.css',
})
export class CalculadoraComponent {
  num1: number = 0;
  num2: number = 0;
  result: number = 0;

  calcService = inject(CalculadoraService);

  somar() {
    this.result = this.calcService.somar(this.num1, this.num2);
    console.log(this.result);
  }
  subtrair() {
    this.result = this.calcService.subtrair(this.num1, this.num2);
    console.log(this.result);
  }
  multiplicar() {
    this.result = this.calcService.multiplicar(this.num1, this.num2);
    console.log(this.result);
  }
  dividir() {
    this.result = this.calcService.dividir(this.num1, this.num2);
    console.log(this.result);
  }
}
