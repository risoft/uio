Ext.application({
    name: 'Sencha',
    requires: ['Ext.data.TreeStore', 'Ext.dataview.NestedList'],
    launch: function() {
    	Ext.define('ListItem', {
    	    extend: 'Ext.data.Model',
    	    config: {
    	        fields: ['text', 'className', 'loadObject', 'type', 'url']
    	    }
    	});

    	var treeStore = Ext.create('Ext.data.TreeStore', {
    	    model: 'ListItem',
    	    defaultRootProperty: 'items',
    	    proxy: {
    	        type: 'ajax',
    	        url : 'menu.js'
    	    }
        }
	);
    	
   	Ext.create('Ext.NestedList', {
    	    fullscreen: true,
    	    store: treeStore,
    	    detailCard: {
    	        xtype: 'container',
    	        layout: 'fit'
    	    },
    	    listeners: {
    	        leafitemtap: function(nestedList, list, index, target, record) {
    	            var detailCard = nestedList.getDetailCard();
    	            if (record.data.type === "form")
    	            {
    	            	console.log("creating form: %o", record.data);
    	            	var elem = Ext.create('uio.UioForm',{jclass: record.data.className});
    	            }
    	            else if (record.data.type === "grid")
	            	{
    	            	var elem = Ext.create('uio.UioGrid',{jclass: record.data.className});
	            	}
    	            else if (record.data.type === "link")
	            	{
    	            	var url = record.data.url;    	            	
    	            	window.location.href = url;
	            	}
    	            else
    	            {
    	            	console.warn("WARNING: ", nestedList, list, index, target, record);
    	            }    	    
    	            detailCard.removeAll();
    	            detailCard.add(elem);
    	        }
    	    }
    	});
    }
});