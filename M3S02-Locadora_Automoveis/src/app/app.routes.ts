import { Routes } from '@angular/router';
import { AutomoveisComponent } from './pages/automoveis/automoveis.component';
import { AutomoveisDetailsComponent } from './pages/automoveis/automoveis-details/automoveis-details.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'automoveis',
    pathMatch: 'full',
  },
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
];
