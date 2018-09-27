import { Directive, Input, OnChanges, SimpleChanges } from '@angular/core';
import { AbstractControl, NG_VALIDATORS, Validator, ValidatorFn, Validators } from '@angular/forms';

/** An account id or amount values can't match the given numeric regular expression */
export function onlyNumberValidator(nameRe: RegExp): ValidatorFn {
  return (control: AbstractControl): {[key: string]: any} | null => {
    const isNumber = nameRe.test(control.value);
    return !isNumber ? {'onlyNumber': {value: control.value}} : null;
  };
}

@Directive({
  selector: '[appOnlyNumber]',
  providers: [{provide: NG_VALIDATORS, useExisting: OnlyNumberValidatorDirective, multi: true}]
})
export class OnlyNumberValidatorDirective implements Validator {
  //@Input('appForbiddenName') forbiddenName: string;

  validate(control: AbstractControl): {[key: string]: any} | null {
    return onlyNumberValidator(new RegExp("^[0-9]*$", 'i'))(control) ;
  } 
}



/*
Copyright 2017-2018 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/