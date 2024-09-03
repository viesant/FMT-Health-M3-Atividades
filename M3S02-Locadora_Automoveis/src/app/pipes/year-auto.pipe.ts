import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'yearAuto',
  standalone: true,
})
export class YearAutoPipe implements PipeTransform {
  transform(value: number): number {
    return 2024 - value;
  }
}
