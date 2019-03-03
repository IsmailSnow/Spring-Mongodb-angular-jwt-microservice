import { Component, OnInit } from '@angular/core';
import { CatalogueService } from '../services/catalogue.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

 
  constructor(private catService:CatalogueService,private router:Router) { }
  catagories;
  currentCategory;
  ngOnInit() {
  this.catService.getAllCategories().subscribe(data=>this.catagories=data,error=>console.log(error));
  }

  onGetprodutcs(c){
  this.currentCategory=c;
  let url = c._links.products.href;
  this.router.navigateByUrl("/products/"+btoa(url));
  }
}
