import { TestBed } from '@angular/core/testing';

import { FaceBackendService } from './face-backend.service';

describe('FaceBackendService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FaceBackendService = TestBed.get(FaceBackendService);
    expect(service).toBeTruthy();
  });
});
