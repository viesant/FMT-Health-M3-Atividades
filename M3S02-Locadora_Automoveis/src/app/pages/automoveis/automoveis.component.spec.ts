import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutomoveisComponent } from './automoveis.component';

describe('AutomoveisComponent', () => {
  let component: AutomoveisComponent;
  let fixture: ComponentFixture<AutomoveisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutomoveisComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutomoveisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
