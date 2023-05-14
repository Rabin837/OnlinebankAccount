import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-full-image-view',
  templateUrl: './full-image-view.component.html',
  styleUrls: ['./full-image-view.component.css']
})
export class FullImageViewComponent implements OnInit{
  imagelink: any;
  ngOnInit(): void {
  }
constructor( @Inject(MAT_DIALOG_DATA) public data: any,){
this.imagelink=data.imageLink;
}
}
