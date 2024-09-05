import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent {
  favoritosCount: number = 0;

  ngOnInit(): void {
    this.favoritosCount = JSON.parse(
      localStorage.getItem('favoritos') || '[]'
    ).length;
  }
}
