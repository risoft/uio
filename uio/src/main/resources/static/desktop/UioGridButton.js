Ext.define('uio.desktop.UioGridButton',
    			{
    				extend: 'Ext.button.Button',
    				xtype: 'uiogridbutton',
    				min: 1,
    				max: 99999, 
    				
    				setDisabledBySelectionSize: function(selectionSize)
    				{
    					this.setDisabled(selectionSize > this.max || selectionSize < this.min);
    				},
    				
    				initComponent: function()
    				{
    					this.callParent();
    					console.log("ic");
    					this.addListener("on", function(){alert("click");});
    				},
    				
    				handler: function(button, event)
    				{
    					console.log("handler");
    					var grid = this.up('grid');
		        		var selectionModel = grid.getSelectionModel();
		        		var selection = selectionModel.getSelection();
		        		var ids = [];
		        		for(var i=0; i < selection.length; i++)
		    			{
		        			ids.push(selection[i].data.id);
		    			};
		    			if (this.ajaxUrl)
	    				{
		    				Ext.Ajax.request({
		        				url: this.ajaxUrl, 
		        				params: {ids: ids},
		        				success: function()
		        				{
		        					grid.getStore().reload();
		        				}
		        			});
	    				}
		    			else if (this.form)
		    			{
		    	  			var config ={jclass: this.form, baseParams: {ids: ids}, header: false};
		        			var form = Ext.create('uio.desktop.UioForm', config);
		        			var window = Ext.create('Ext.window.Window', {
		        					layout: 'fit', items: [form], title: config.title, shrinkWrap: 3});
		        					
		        			window.show();
		    			}
    				}
    			});
