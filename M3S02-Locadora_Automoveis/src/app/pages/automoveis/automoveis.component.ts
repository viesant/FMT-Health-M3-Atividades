import { Component, inject, OnInit } from '@angular/core';
import { AutomoveisService } from '../../services/automoveis.service';
import { Router } from '@angular/router';
import { YearAutoPipe } from '../../pipes/year-auto.pipe';

@Component({
  selector: 'app-automoveis',
  standalone: true,
  imports: [YearAutoPipe],
  templateUrl: './automoveis.component.html',
  styleUrl: './automoveis.component.css',
})
export class AutomoveisComponent implements OnInit {
  automoveisList: any = [];
  automoveisService = inject(AutomoveisService);
  router = inject(Router);

  ngOnInit(): void {
    this.automoveisService.list().subscribe({
      next: (data: any) => {
        let favoritos = JSON.parse(localStorage.getItem('favoritos') || '[]');

        this.automoveisList = data;
        this.automoveisList.forEach((automovel: any) => {
          automovel.isFavorito = favoritos.includes(automovel.id);
        });

        console.log(this.automoveisList);
      },
      error: (error) => {
        console.error('Erro ao listar pacientes: ', error);
      },
    });
  }

  redirectTo(id: number) {
    this.router.navigate(['/automoveis', id]);
  }
}
