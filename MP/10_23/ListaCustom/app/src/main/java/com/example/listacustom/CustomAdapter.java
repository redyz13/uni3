package com.example.listacustom;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contatto> {
    private LayoutInflater inflater;

    public CustomAdapter(Context context, int resource, List<Contatto> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            Log.d("DEBUG", "Inflating view");
            convertView = inflater.inflate(R.layout.list_element, null);
        }

        Contatto contatto = getItem(position);

        Log.d("DEBUG", "Contact contatto = " + contatto);

        TextView name;
        TextView tel;
        ImageView foto;

        name = convertView.findViewById(R.id.elem_lista_nome);
        tel = convertView.findViewById(R.id.elem_lista_telefono);
        foto = convertView.findViewById(R.id.elem_lista_foto);

        if (contatto != null) {
            name.setText(contatto.getName());
            tel.setText(contatto.getTel());
            foto.setImageDrawable(contatto.getPicture());

            name.setTag(position);
            tel.setTag(position);
            foto.setTag(position);
        }

        return convertView;
    }
}
