import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FullImageViewComponent } from './full-image-view.component';

describe('FullImageViewComponent', () => {
  let component: FullImageViewComponent;
  let fixture: ComponentFixture<FullImageViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FullImageViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FullImageViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
