import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutomoveisDetailsComponent } from './automoveis-details.component';

describe('AutomoveisDetailsComponent', () => {
  let component: AutomoveisDetailsComponent;
  let fixture: ComponentFixture<AutomoveisDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutomoveisDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutomoveisDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
