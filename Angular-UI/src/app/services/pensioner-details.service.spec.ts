import { TestBed } from '@angular/core/testing';

import { PensionerDetailsService } from './pensioner-details.service';

describe('PensionerDetailsService', () => {
  let service: PensionerDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PensionerDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
