import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormCommandeComponent } from './form.commande.component';

describe('FormCommandeComponent', () => {
  let component: FormCommandeComponent;
  let fixture: ComponentFixture<FormCommandeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormCommandeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FormCommandeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
