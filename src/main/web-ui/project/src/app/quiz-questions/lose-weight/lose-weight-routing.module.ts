import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {LoseWeightComponent} from "./lose-weight.component";

const route: Routes = [
  {
    path: '',
    component: LoseWeightComponent,
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(route)
  ]
})
export class LoseWeightRoutingModule { }
