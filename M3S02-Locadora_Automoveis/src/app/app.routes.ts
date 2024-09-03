import { Routes } from '@angular/router';
import { AutomoveisComponent } from './pages/automoveis/automoveis.component';
import { AutomoveisDetailsComponent } from './pages/automoveis/automoveis-details/automoveis-details.component';

export const routes: Routes = [
  {
    path: 'automoveis',
    children: [
      {
        path: '',
        component: AutomoveisComponent,
      },
      {
        path: ':id',
        component: AutomoveisDetailsComponent,
      },
    ],
  },
  { path: '', redirectTo: '/automoveis', pathMatch: 'full' },
  { path: '**', redirectTo: '/automoveis' },
];
