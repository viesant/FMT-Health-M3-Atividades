import { Component, inject } from '@angular/core';
import { AutomoveisService } from '../../../services/automoveis.service';
import { ActivatedRoute } from '@angular/router';
import { YearAutoPipe } from '../../../pipes/year-auto.pipe';

@Component({
  selector: 'app-automoveis-details',
  standalone: true,
  imports: [YearAutoPipe],
  templateUrl: './automoveis-details.component.html',
  styleUrl: './automoveis-details.component.css',
})
export class AutomoveisDetailsComponent {
  automovel: any = {};
  automoveisService = inject(AutomoveisService);
  activatedRoute = inject(ActivatedRoute);

  ngOnInit(): void {
    this.activatedRoute.params.subscribe({
      next: (params: any) => {
        this.automoveisService.detail(params.id).subscribe({
          next: (data) => {
            this.automovel = data;
          },
          error: (error) => {
            console.log('Erro do detail', error);
          },
        });
      },
      error: (error) => {
        console.log('Erro do params', error);
      },
    });
  }

  favoritar() {
    let favoritos = JSON.parse(localStorage.getItem('favoritos') || '[]');

    if (!favoritos.includes(this.automovel.id)) {
      favoritos.push(this.automovel.id);
      localStorage.setItem('favoritos', JSON.stringify(favoritos));
      alert('Paciente favoritado com sucesso!');
    } else {
      favoritos = favoritos.filter((id: any) => id !== this.automovel.id);
      localStorage.setItem('favoritos', JSON.stringify(favoritos));
      alert('Automóvel removido dos favoritos.');
    }
  }
}
