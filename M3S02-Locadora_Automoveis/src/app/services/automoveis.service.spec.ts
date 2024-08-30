import { TestBed } from '@angular/core/testing';

import { AutomoveisService } from './automoveis.service';

describe('AutomoveisService', () => {
  let service: AutomoveisService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutomoveisService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
