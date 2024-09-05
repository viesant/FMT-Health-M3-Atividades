import { Component, inject, OnInit } from '@angular/core';
import { AutomoveisService } from '../../services/automoveis.service';
import { Router } from '@angular/router';
import { YearAutoPipe } from '../../pipes/year-auto.pipe';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-automoveis',
  standalone: true,
  imports: [FormsModule, YearAutoPipe],
  templateUrl: './automoveis.component.html',
  styleUrl: './automoveis.component.css',
})
export class AutomoveisComponent implements OnInit {
  automoveisList: any = [];
  searchInput = '';
  automoveisListFiltered: any = [];
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
        this.automoveisListFiltered = this.automoveisList;
        // console.log(this.automoveisList);
      },
      error: (error) => {
        console.error('Erro ao listar pacientes: ', error);
      },
    });
  }

  search() {
    if (!this.searchInput) {
      this.automoveisListFiltered = this.automoveisList;
    } else {
      this.automoveisListFiltered = this.automoveisList.filter((auto: any) =>
        auto.name.toLowerCase().includes(this.searchInput.toLowerCase())
      );
      console.log(this.automoveisListFiltered);
    }
  }

  redirectTo(id: number) {
    this.router.navigate(['/automoveis', id]);
  }
}
