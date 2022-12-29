import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoseWeightComponent} from "./lose-weight.component";
import {LoseWeightRoutingModule} from "./lose-weight-routing.module";

@NgModule({
  declarations: [LoseWeightComponent],
  imports: [
    CommonModule,
    LoseWeightRoutingModule
  ]
})
export class LoseWeightModule { }
